<script>
	import {
    Form,
    FormGroup,
    TextInput,
    TextArea,
    DatePicker,
    DatePickerInput,
    TimePicker,
    NumberInput,
    FileUploader,
    Button,
  } from "carbon-components-svelte";

    import { submitPost } from "../stores/post.js";
    import { uploadFile } from "../stores/multimedia.js";
    import { getUser } from "../stores/user.js";

    let files;
    async function submit(event)
    {
        event.preventDefault();
        const form = new FormData(event.target);

        let post = {};
        form.forEach((value, key) => {
            if (key != "deadline" && key != "time" && key != "files")
                post[key] = value;
        })

        let date = new Date();
        let time = "00:00";

        if (form.get("deadline"))
            date = form.get("deadline").split("/").map((item) => {return parseInt(item)});
        if (form.get("time"))
            time = form.get("time")

        time = time.split(":").map((item) => {return parseInt(item)});

        post["deadline"] = new Date(date[2], date[1] - 1, date[0], time[0], time[1]);
        post["user"] = await getUser();

        //let fileName = (await uploadFile(files)).filename;
        //post["multimedia"] = fileName;

        let storedPost = await submitPost(post);

        event.target.reset();
        open = false;
    }

    export let open = true;
</script>

<Form on:submit={submit}>

    <TextInput id="title" name="title" labelText="Title" placeholder="Enter title..." required />

    <br>

    <TextArea id="description" name="description" labelText="Description" placeholder="Enter a description..." maxCount={250}/>

    <br>

    <NumberInput
        min={0}
        value={0}
        step={10}
        label="Minimum Bid Price"
        id="basePrice"
        name="basePrice"
    />

    <br>

    <span class="date-time">
        <DatePicker datePickerType="single" value={new Date()} dateFormat="d/m/Y" minDate={new Date().getDate() - 1} on:change>
            <DatePickerInput labelText="Deadline" placeholder="dd/mm/yyyy" name="deadline" id="deadline" />
        </DatePicker>
        <TimePicker name="time" id="time" placeholder="hh:mm" pattern="([0-1]?[0-9]|2[0-3]):[0-5][0-9](\\s)?">
        </TimePicker>
    </span>

    <br>

    <FileUploader kind="tertiary" labelTitle="Add photos and videos" buttonLabel="Add files" accept={[".jpg", ".jpeg", ".png", ".webp", ".mp4"]} status="edit" name="files" id="files" bind:files={files}/>

    <br>

    <br>

    <Button type="submit">Post</Button>
</Form>

<style>
    .date-time {
        display: flex;
        flex-flow: column;
    }
</style>
