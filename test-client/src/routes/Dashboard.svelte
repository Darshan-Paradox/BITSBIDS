<script>
	import { getUser } from "../stores/user.js";
	import { getPostByPage, get50Post, getPostByUser } from "../stores/post.js";

	import { onMount } from "svelte";

	import Header from "../components/Header.svelte";
	import Post from "../components/Post.svelte";

	import { Button, Tag } from "carbon-components-svelte";
	import { NumberInput } from "carbon-components-svelte";
	import { PaginationNav } from "carbon-components-svelte";
	import { ExpandableTile } from "carbon-components-svelte";
	import { ContentSwitcher, Switch } from "carbon-components-svelte";

	let page = 1;

	let postList = [{}];
	let postList50 = [{}];
	let myPostList = [{}];

	setInterval(async () => {
		let newPostList = await getPostByPage(page);

		if (newPostList != postList)
			postList = newPostList;
	}, 3000);
	setInterval(async () => {
		let newPostList50 = await get50Post();

		if (newPostList50 != postList50)
			postList50 = newPostList50;
	}, 3000);
	setInterval(async () => {
		let newMyPostList = await getPostByUser();
		if (newMyPostList != myPostList)
			myPostList = newMyPostList;
	}, 5000);

	setInterval(async () => {
		let myPostList = await getPostByUser();
		let postList = await getPostByPage(page);
		let postList50 = await get50Post(page);

		if (newPostList[0].title != postList[0].title)
			postList = newPostList;
	}, 120*1000);

	let user = null;
	onMount(async () => {user = await getUser(), myPostList = await getPostByUser(), postList = await getPostByPage(page), postList50 = await get50Post()});

	let selectedIndex = 0;
</script>

<Header user={user} posts={postList50}/>

<div class="posts">
	<div class="page-nav">
		<PaginationNav bind:page />
	</div>

	<div class="lists">
		{#if selectedIndex == 0}
			{#each postList as post}
				<Post user={user} post={post}/>
			{/each}
		{:else}
			{#each myPostList as post}
				<Post user={user} post={post} owner/>
			{/each}
		{/if}
	</div>

	<div class="switcher">
		<ContentSwitcher bind:selectedIndex>
		  <Switch text="All Posts" />
		  <Switch text="My Posts" />
		</ContentSwitcher>
	</div>

</div>

<div class="chat">
</div>

<style>
	::-webkit-scrollbar {
		display: none;
	}
	.posts {
		height: 100%;
		width: 100%;

		margin-top: 5%;

		padding: 2%;

		display: flex;
		flex-flow: column-reverse;
		gap: 2%;
	}
	.page-nav {
		align-self: center;
	}
	.lists {
		width: 70%;
		height: 30%;

		overflow-y: scroll;

		display: flex;
		flex-flow: column;
		gap: 10px;

		justify-content: center;
		align-items: center;

		flex: 1 1 auto;
		align-self: center;
	}
	.switcher {
		width: 40%;
		align-self: center;
	}
	.chat {
		position: absolute;
	}
</style>
