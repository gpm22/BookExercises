import React from "react";
import Confirmation from "./Confirmation";
import Header from "../commons/Header";
import Footer from "../commons/Footer";
/* Changes made to this file will not affect your tests.
 * This file is used to control the behavior of the web preview. 
*/
const Challenge1 = props => (
  <div id="challenge1">
    <Header />
    <h1>Challenge 1 - Confirmation Component</h1>
    <Confirmation 
      message='Is the pie a lie?'
      type='message'
      accept={() => 
        console.log("accepted") // prints to browser console
      } 
      decline={() => console.log("declined")} 
    />
    <Footer/>
  </div>
);

export default Challenge1;
