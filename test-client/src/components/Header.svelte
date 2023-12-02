<script>
	import { getBidByPost, submitBid } from "../stores/bid.js";
	import { getChats, sendMessage } from "../stores/message.js";

	import { TextInput, NumberInput, Tag, Link, Button, Modal } from "carbon-components-svelte";
	import { Header, HeaderUtilities, HeaderAction, HeaderSearch } from "carbon-components-svelte";
	import { HeaderPanelLinks, HeaderPanelDivider, HeaderPanelLink } from "carbon-components-svelte";

	import PostForm from "../components/PostForm.svelte";
	import Post from "../components/Post.svelte";

	import UserAvatarFilled from "carbon-icons-svelte/lib/UserAvatarFilled.svelte";
	import UserAvatarFilledAlt from "carbon-icons-svelte/lib/UserAvatarFilledAlt.svelte";
	import Currency from "carbon-icons-svelte/lib/Currency.svelte";

	let open = false;
	export let user = null;
	export let posts = [];

	let value = "";

	$: results = value.length > 0 ? posts.filter((item) => {
          return (
            item.title.toLowerCase().includes(value.toLowerCase()) ||
            item.description.includes(value.toLowerCase())
          );
        })
      : [];

	$: results = results ? results.map((item) => {
		item.text = item.title;
		return item;
	}) : [];

	let display = false;
	let post = {};
	function showPost(event)
	{
		display = true;
		post = event.detail.selectedResult;
	}

	let chats = [[]];
	setInterval(async () => {
		chats = await getChats();
	}, 5000);
	$: console.log(chats);


	let displayChat = false;
	let content = [];
	let currText = "";

	function showTexts(list)
	{
		displayChat = true;
		content = list;
	}
	async function sendText(id, chatPost)
	{
		console.log(await sendMessage(id, chatPost, currText, false));
		currText = "";
	}

</script>

<Header company="G65" platformName="BITSBIDS">
	<HeaderUtilities>

		<HeaderSearch
			bind:value
			{results}
			on:select={showPost}
		/>

		<div class="create-post-btn">
			<Button kind="ghost" on:click={() => (open = true)}>
				<p class="create-post">Create Post</p>
			</Button>
		</div>

		<HeaderAction icon={UserAvatarFilledAlt} closeIcon={UserAvatarFilled}>
			{#if user}
				<HeaderPanelLinks>
					<img src={user.icon} alt="profile icon"/>
					<HeaderPanelDivider>User Info:</HeaderPanelDivider>
						<HeaderPanelLink>{user.username}</HeaderPanelLink>
						<HeaderPanelLink>{user.email}</HeaderPanelLink>
					<HeaderPanelDivider>Coins:</HeaderPanelDivider>
						<HeaderPanelLink>{user.coins}</HeaderPanelLink>
						<HeaderPanelLink><Link href="" icon={Currency}>Add Coins</Link></HeaderPanelLink>

					<HeaderPanelDivider>Chats:</HeaderPanelDivider>
						{#each chats as messageList}
							{#if messageList[0]}
								{@const displayId = messageList[0].id}
								{@const bidderId = messageList[0].bidder ? messageList[0].bidder.username : ""}
								{@const ownerId = messageList[0].owner ? messageList[0].owner.username : ""}
								<HeaderPanelLink>
									<Link href="" on:click={showTexts(messageList)}>
										{`${messageList[0].post.title}::${displayId}   ${ownerId}:${bidderId}`}
									</Link>
								</HeaderPanelLink>
							{/if}
						{/each}
				</HeaderPanelLinks>
			{:else}
				<HeaderPanelLinks>
					<HeaderPanelDivider>
						<Button size="small" href="/login">Login</Button>
					</HeaderPanelDivider>
				</HeaderPanelLinks>
			{/if}
		</HeaderAction>
	</HeaderUtilities>
</Header>

<Modal 
	passiveModal
	bind:open 
	modalHeading="Create post"
	on:open
	on:close
>
<PostForm bind:open/>
</Modal>

<Modal 
	passiveModal
	bind:open={display}
	modalHeading="Search"
	on:open
	on:close
>
	<Post user={user} post={post} />
</Modal>

<Modal 
	passiveModal
	bind:open={displayChat}
	modalHeading={"Chat"}
	on:open
	on:close
>
	<div>
		{#each content as thread}
			{#if thread.direction}
				<Tag type="purple">
					{thread.text}
				</Tag>
			{:else}
				<Tag type="teal">
					{thread.text}
				</Tag>
			{/if}
		{/each}
	</div>

	<br>
	<br>
	<TextInput light placeholder="Enter message..." bind:value={currText}/>
<Button size="field" on:click={async() => {await sendText(content[0].id, content[0].post)}}>Send</Button>
</Modal>

<style>
	.create-post {
		width: 100%;
		height: 100%;

		font-size: 1rem;
		color: white;
	}
	.create-post-btn:hover .create-post {
		color: black;
	}

	img {
		margin-left: 40%;
		margin-right: 40%;

		margin-top: 10%;
		margin-bottom: 10%;

		border-radius: 50%;

		align-self: center;
		width: 20%;
	}
</style>
