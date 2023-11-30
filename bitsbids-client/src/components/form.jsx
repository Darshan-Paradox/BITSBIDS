import React, {useState} from 'react';
import { TextField, Button, Container, Stack } from '@mui/material';
import { Link } from "react-router-dom"
 
 
const RegisterForm = () => {
    const [productName, setProductName] = useState('')
    const [basebid, setLastName] = useState('')
    const [description, setDescription] = useState('')
    const [deadline, setDeadline] = useState('01-01-2000')
    const [file, setFile] = useState('')
 
    function handleSubmit(event) {
        event.preventDefault();
        console.log(productName, basebid, description, deadline, file) 
    }
 
    return (
        <React.Fragment>
            <h2>Post your BID</h2>
            <form onSubmit={handleSubmit} action={<Link to="/login" />}>
                <Stack spacing={2} direction="row" sx={{marginBottom: 4}}>
                    <TextField
                        type="text"
                        variant='outlined'
                        color='secondary'
                        label="Title"
                        onChange={e => setProductName(e.target.value)}
                        value={productName}
                        fullWidth
                        required
                    />
                    <TextField
                        type="text"
                        variant='outlined'
                        color='secondary'
                        label="Base Bid"
                        onChange={e => setLastName(e.target.value)}
                        value={basebid}
                        fullWidth
                        required
                    />
                </Stack>
                <TextField
                    type="text"
                    variant='outlined'
                    color='secondary'
                    label="Description"
                    onChange={e => setDescription(e.target.value)}
                    value={description}
                    fullWidth
                    required
                    sx={{mb: 6}}
                />
                {/* <TextField
                    type="text"
                    variant='outlined'
                    color='secondary'
                    label="Upload Photo"
                    onChange={e => setDescription(e.target.value)}
                    value={description}
                    fullWidth
                    required
                    sx={{mb: 4}}
                /> */}
                
                <TextField
                    type="date"
                    variant='outlined'
                    color='secondary'
                    label="Deadline"
                    onChange={e => setDeadline(e.target.value)}
                    value={deadline}
                    fullWidth
                    required
                    sx={{mb: 4}}
                />
                <div class="input-group mb-1">
                    <input type="file" class="form-control" onChange={e => setFile(e.target.value)}/>
                    {/* <button onClick={handleSubmit}>Upload</button> */}
                </div>
                <div>
                    <h4> </h4>
                </div>
                <Button variant="outlined" color="secondary" type="submit">SUBMIT</Button>

            </form>
        </React.Fragment>
    )
}
 
export default RegisterForm;