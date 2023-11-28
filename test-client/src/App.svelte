<script>
	import router from "page";

	import Home from "./routes/Home.svelte";
	import Login from "./routes/Login.svelte";
	import Dashboard from "./routes/Dashboard.svelte";
	import Post from "./routes/Post.svelte";
	import Redirect from "./routes/Redirect.svelte";

	let page;
	let params;

	router("/", () => { page=Home });
	router("/login", () => { page=Login });
	router("/dashboard", () => { page=Dashboard });
	router("/post", () => { page=Post });
	router("/redirect", () => { page=Redirect });

	router.start();
</script>

<svelte:component this={page} params={params}/>
