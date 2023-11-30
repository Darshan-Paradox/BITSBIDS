import React from "react";
import PrimarySearchAppBar from "../components/navbar";
import MediaCard from "../components/cards";
import "./home.css";

export default function HomePage() {
return (
  <div className="HomePage">
    <PrimarySearchAppBar/>
    <div className="cardBox">
        <div><MediaCard/></div>
        <div><MediaCard/></div>
        <div><MediaCard/></div>
    </div>
  </div>
);
}