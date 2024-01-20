async function getChats()
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://localhost:8080/api/chat`;

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

async function sendMessage(id, post, text, type)
{
  const csrf_token = document.cookie.split('=').at(-1);

  let URL = "";
  if (type)
    URL = `http://localhost:8080/api/chat/new/${id}`;
  else
    URL = `http://localhost:8080/api/chat/${id}`;

  let message = {
    post: post,
    text: text,
    created: new Date()
  };

  message = JSON.stringify(message);

  let options = {
    method: "POST",
    mode: "cors",
    credentials: "include",
    headers: {
	  "Content-Type": "application/json",
      "Access-Control-Allow-Origin":"*",
      "X-XSRF-TOKEN": csrf_token
    },
    body: message
  };

  const response = await fetch(URL, options);
  const json = await response.json();

  return json;
}

export { getChats, sendMessage};
