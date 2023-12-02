<script>
	import Header from "../components/Header.svelte";

	import { Button } from "carbon-components-svelte";

	import Login from "carbon-icons-svelte/lib/Login.svelte";

	function redirect()
	{
		window.location.assign("http://localhost:8080/oauth2/authorization/google");
	}
</script>


<Header/>
<div>
	<Button kind="tertiary" icon={Login} on:click={redirect}>Log In with Google</Button>
</div>

<style>
	div {
		align-self: center;
	}
</style>
