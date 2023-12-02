async function getUser()
{
  const csrf_token = document.cookie.split('=').at(-1);

  const response = await fetch("http://localhost:8080/api/user", {
    mode: "cors",
	credentials: "include",
    headers: {
      "Access-Control-Allow-Origin": "http://localhost:8080/",
	  "X-XSRF-TOKEN": csrf_token
	}
  });

  return await response.json();
}

export {getUser};
