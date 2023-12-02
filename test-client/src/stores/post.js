async function getPostByPage(page)
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://localhost:8080/api/post/page/${page}`;

  const response = await fetch(URL, {
    mode: "cors",
	credentials: "include",
    headers: {
      "Access-Control-Allow-Origin": "http://localhost:8080/",
	  "X-XSRF-TOKEN": csrf_token
	}
  });

  return await response.json();
}

async function freezePost(id)
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://localhost:8080/api/post/freeze/${id}`;

  const response = await fetch(URL, {
    mode: "cors",
	credentials: "include",
    headers: {
      "Access-Control-Allow-Origin": "http://localhost:8080/",
	  "X-XSRF-TOKEN": csrf_token
	}
  });

  return await response.json();
}

async function get50Post()
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://localhost:8080/api/post/50`;

  const response = await fetch(URL, {
    mode: "cors",
	credentials: "include",
    headers: {
      "Access-Control-Allow-Origin": "http://localhost:8080/",
	  "X-XSRF-TOKEN": csrf_token
	}
  });

  return await response.json();
}

async function getPostByUser()
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://localhost:8080/api/post`;

  const response = await fetch(URL, {
    mode: "cors",
	credentials: "include",
    headers: {
      "Access-Control-Allow-Origin": "http://localhost:8080/",
	  "X-XSRF-TOKEN": csrf_token
	}
  });

  return await response.json();
}

async function submitPost(post)
{
  const csrf_token = document.cookie.split('=').at(-1);

  let URL = `http://localhost:8080/api/post`;

  post = JSON.stringify(post);

  let options = {
    method: "POST",
    mode: "cors",
    credentials: "include",
    headers: {
	  "Content-Type": "application/json",
      "Access-Control-Allow-Origin":"*",
      "X-XSRF-TOKEN": csrf_token
    },
    body: post
  };

  const response = await fetch(URL, options);
  const json = await response.json();

  return json;
}

export { getPostByPage, get50Post, freezePost, getPostByUser, submitPost };
