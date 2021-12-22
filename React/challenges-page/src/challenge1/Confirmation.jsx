import React, { useState } from "react";
import Notification from "./Notification";
import "./Confirmation.css";

const Confirmation = ({ type, message, accept, decline, cleanCallBack }) => {
  let [notRender, setNotRender] = useState(false);

  const handleYes = (e) => {
    accept(e);
    cleanCallBack();
  }

  const handleNo = (e) => {
    decline(e);
    cleanCallBack();
  }


const buttons = (
  <>
    <button className="btn btn-yes" onClick={handleYes} >
      Yes
    </button>
    <br />
    <button className="btn btn-no" onClick={handleNo}>
      No
    </button>
  </>
);

return (<Notification message={message} type={type} children={buttons} />);
};

export default Confirmation;
