<script>
	const csrf_token = document.cookie.split('=').at(-1);

	async function users()
	{
		const response = await fetch("http://localhost:8080/api/user", {
			mode: "cors",
			credentials: "include",
			headers: {
				"Access-Control-Allow-Origin":"*",
				"X-XSRF-TOKEN": csrf_token
			}
		});
		const json = await response.json();
		console.log(json);
	}

	async function posts()
	{
		const response = await fetch("http://localhost:8080/api/post/1", {
			mode: "cors",
			credentials: "include",
			headers: {
				"Access-Control-Allow-Origin":"*",
				"X-XSRF-TOKEN": csrf_token
			}
		});
		const json = await response.json();
		console.log(json);
	}

	let user = users();
	let post = posts();
</script>
