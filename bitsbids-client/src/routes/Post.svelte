<script>
	const csrf_token = document.cookie.split('=').at(-1);

	async function submit(event)
	{
		event.preventDefault();
		const form = new FormData(event.target);
		//fetch user first
		let options = {
			mode: "cors",
			credentials: "include",
			headers: {
				"Access-Control-Allow-Origin":"*",
				"X-XSRF-TOKEN": csrf_token
			}
		};
		const response = await fetch("http://localhost:8080/api/user", options);
		const User = await response.json();

		console.log(User);

		let object = {};
		form.forEach(function(value, key){
			object[key] = value;
		});
		object["user"] = User;
		object["deadline"] = new Date();
		let body = JSON.stringify(object);

		console.log(object);
		console.log(body);

		options = {
			method: "POST",
			mode: "cors",
			credentials: "include",
			headers: {
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin":"*",
				"X-XSRF-TOKEN": csrf_token
			},
			body: body
		};
		const post = await fetch("http://localhost:8080/api/post", options);
		const json = await post.text();
		console.log(json);
	}
</script>

<form on:submit={submit}>
	<input type="text" name="title" id="title"/>
	<input type="text" name="description" id="description"/>
	<input type="submit" value="post"/>
</form>
