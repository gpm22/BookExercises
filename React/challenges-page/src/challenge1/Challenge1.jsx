import React, { useState } from "react";
import Confirmation from "./Confirmation";
import Header from "../commons/Header";
import Footer from "../commons/Footer";
/* Changes made to this file will not affect your tests.
 * This file is used to control the behavior of the web preview.
 */
const Challenge1 = (props) => {

  const [message, setMessage] = useState("");
  const [messageType, setMessageType] = useState("success");
  const [acceptButton, setAcceptButton] = useState("alert");
  const [declineButton, setDeclineButton] = useState("alert");


  const cleanCallBack = () => {
    setMessage("");
  }

  const buttonAcceptBeheviorTypes = {
    "alert": () => alert("accepted"),
    "console": () => console.log("accepted")
  };

  const buttonDeclineBeheviorTypes = {
    "alert": () => alert("declined"),
    "console": () => console.log("declined")
  };

  return (
    <div id="challenge1" class="block">
      <Header />
      <main>
        <h1>Challenge 1 - Confirmation Component</h1>
        <section className="description">
          <h4>This challenge is a example of a react component consuming other react component.</h4>
          <h4>The Confirmation Component consumes the Notification Component.</h4>
          <h4>The Confirmation Component receives a message, message type and the behavior of the accept and decline buttons.</h4>
          <h4>The Notification Component receives a message, message type and the children component, which is the Confirmation Component.</h4>
          <h4>After choosing an option, the component will disappear.</h4>
        </section>
        <section className="inputs">
          <p>Enter the message: <input value={message} onChange={(e) => setMessage(e.target.value)} type="text"></input> </p>
          <p>Enter the message type: <select onChange={(e) => setMessageType(e.target.value)} type="text">
            <option value="success">Success</option>
            <option value="message">Info</option>
            <option value="caution">Caution</option>
            <option value="error">Error</option>
          </select></p>
          <p>Enter the behavior of the accept button: <select value={acceptButton} onChange={(e) => setAcceptButton(e.target.value)} type="text">
            <option value="alert">Pop an alert</option>
            <option value="console">Log on console</option>
          </select></p>
          <p>Enter the behavior of the decline button: <select value={declineButton} onChange={(e) => setDeclineButton(e.target.value)} type="text">
            <option value="alert">Pop an alert</option>
            <option value="console">Log on console</option>
          </select></p>
        </section>
        <Confirmation
          cleanCallBack={cleanCallBack}
          message={message}
          type={messageType}
          accept={buttonAcceptBeheviorTypes[acceptButton]}
          decline={buttonDeclineBeheviorTypes[declineButton]}
        />
      </main>
      <Footer />
    </div>
  );
}
export default Challenge1;
