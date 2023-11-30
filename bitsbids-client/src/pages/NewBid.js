import React from "react";
import RegisterForm from "../components/form";
import PrimarySearchAppBar from "../components/navbar";
import {ThemeProvider } from '@mui/material/styles';
import { createTheme,Container } from "@mui/material";
import "./home.css"

const defaultTheme = createTheme();

export default function NewBid() {
return (
  <>
    <PrimarySearchAppBar/>
    <div className="bidform">
      <h2>  </h2>
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <RegisterForm/>
      </Container>
    </ThemeProvider>
    </div>
  </>
);
}