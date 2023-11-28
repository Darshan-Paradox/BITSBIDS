<script>
	function redirect()
	{
		window.location.replace("http://localhost:8080/oauth2/authorization/google");
	}
</script>

<button on:click={redirect}>
	Login with Gmail
</button>
