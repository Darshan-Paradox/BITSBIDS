<script>
	export let user;
	export let post;
	export let owner = false;

	import { freezePost } from "../stores/post.js";
	import { getBidByPost, submitBid } from "../stores/bid.js";
	import { sendMessage } from "../stores/message.js";

	import { TextArea, Button, Tag } from "carbon-components-svelte";
	import { Modal } from "carbon-components-svelte";
	import { NumberInput } from "carbon-components-svelte";
	import { PaginationNav } from "carbon-components-svelte";
	import { ExpandableTile } from "carbon-components-svelte";

	async function createBid(post)
	{
		console.log(post);
		const amount = document.getElementById(post.id).value;
		console.log(amount);

		let bid = {
			post: post,
			user: user,
			amount: amount,
			created: new Date()
		};

		console.log(await submitBid(bid));
	}

	let open = false;
	let text = "";
	let disabled = false;
	function openMessageBox()
	{
		open = true;
	}

	async function sendText()
	{
		console.log(await sendMessage(post.id, post, text, true));
		text = "";
		disabled = true;
		open = false;
	}
</script>

<ExpandableTile light on:click={(event) => {event.preventDefault()}}>
	<div slot="above" class="above">

		<span>

			<Tag type="green">
				{#await getBidByPost(post.id) then bids}
					{#if bids.length}
						{`₹${bids[0].amount}`}
					{:else}
						{`₹${post.basePrice}`}
					{/if}
				{/await}
			</Tag>

			{#if post.user}
				<Tag type="blue">{post.user.username}</Tag>
			{/if}

			{#if owner}
				<Tag type="red">Owner</Tag>
			{/if}

			<h4>{post.title}</h4>
			<h6>{post.description}</h6>

			<br>
			{#if owner}
				<Button on:click={async() => {await freezePost(post.id)}}>Freeze Bid</Button>
			{/if}

		</span>

		<span>

			<NumberInput
			min={post.basePrice}
			value={post.basePrice}
			step={10}
			label="Bidding amt:"
			id={post.id}
			/>

			<br>

			<Button disabled={owner} on:click={async() => {await createBid(post)}}>
				Bid
			</Button>
			<Button kind="secondary" disabled={owner} on:click={() => openMessageBox()}>
				Message
			</Button>

		</span>

	</div>
</ExpandableTile>

<Modal 
	passiveModal
	bind:open
	modalHeading={post.title}
	on:open
	on:close
>
	<TextArea light labelText="Send message" placeholder="Message..." maxCount={250} bind:value={text} />
	<Button disabled={disabled} on:click={async() => {await sendText()}}>Send</Button>
</Modal>

<style>
	.above {
		display: flex;
		justify-content: space-between;

		height: fit-content;
	}
</style>
