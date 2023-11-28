<script>
	import {onMount} from "svelte";

	const csrf_token = document.cookie.split('=').at(-1);

	async function is_authenticated()
	{
		const response = await fetch("http://localhost:8080/api/user", {
			mode: "cors",
			credentials: "include",
			headers: {
				"Access-Control-Allow-Origin": "http://localhost:8080/",
				"X-XSRF-TOKEN": csrf_token
			}
		});

		const text = await response.text();

		return text;
	}

	let name = "";
	onMount(async() =>
	{
		try {
			name = await is_authenticated()
			window.location.assign("/dashboard");
		} catch(e) {
			window.location.assign("/login");
		}
	});
</script>
