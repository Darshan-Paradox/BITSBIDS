async function uploadFile(file)
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://127.0.0.1:5000/image`;

  let form = new FormData();
  form.append("file", file);

  let options = {
    method: "POST",
    mode: "cors",
    //credentials: "include",
    headers: {
	  "Content-Type": "multipart/form-data",
      "Access-Control-Allow-Origin":"*",
      "X-XSRF-TOKEN": csrf_token
    },

    files: form
  };

  const response = await fetch(URL, options);
  const fileName = await response.json();

  return fileName;
}

async function downloadFile(fileName)
{
  const csrf_token = document.cookie.split('=').at(-1);
  let URL = `http://127.0.0.1:5000/image/${fileName}`;

  let options = {
    mode: "cors",
    //credentials: "include",
    headers: {
      "Access-Control-Allow-Origin":"*",
      "X-XSRF-TOKEN": csrf_token
    },
  };

  const response = await fetch(URL, options);
  const src = URL.createObjectURL(await response.blob());

  return src;
}
export { uploadFile, downloadFile };
