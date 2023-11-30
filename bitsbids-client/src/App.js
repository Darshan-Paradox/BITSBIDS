import React from "react"
import { Route, Routes } from "react-router-dom"
import PrimarySearchAppBar from "./components/navbar";
import MediaCard from "./components/cards";
import "./App.css"
// We will create these two pages in a moment
import HomePage from "./pages/HomePage"
import UserPage from "./pages/UserPage"
import { Card } from "@mui/material";
import RegisterForm from "./components/form";
import NewBid from "./pages/NewBid";
import LoginPage from "./pages/LoginPage";
import SignUp from "./pages/SignupPage";

export default function App() {
  return (  
    <>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/new-bid" element={<NewBid/>} />
        <Route path="/login" element={<LoginPage/>} />
        <Route path="/signup" element={<SignUp/>} />
      </Routes>
    </>    
  )
}


// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }


// export default App;
