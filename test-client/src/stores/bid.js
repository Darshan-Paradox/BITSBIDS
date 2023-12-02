async function getBidByPost(postId)
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://localhost:8080/api/bid/${postId}`;

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

async function submitBid(bid)
{
  const csrf_token = document.cookie.split('=').at(-1);

  let URL = `http://localhost:8080/api/bid`;

  bid = JSON.stringify(bid);

  let options = {
    method: "POST",
    mode: "cors",
    credentials: "include",
    headers: {
	  "Content-Type": "application/json",
      "Access-Control-Allow-Origin":"*",
      "X-XSRF-TOKEN": csrf_token
    },
    body: bid 
  };

  const response = await fetch(URL, options);
  //const json = await response.json();
  const json = await response.text();

  return json;
}

export { getBidByPost, submitBid };
