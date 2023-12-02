<script>
	import { onMount } from "svelte";
	import { getUser } from "../stores/user.js";

	let user = null;

	onMount(async() =>
	{
		try {
			user = await getUser();
			window.location.assign("/dashboard");
		} catch(e) {
			window.location.assign("/login");
		}
	});
</script>
