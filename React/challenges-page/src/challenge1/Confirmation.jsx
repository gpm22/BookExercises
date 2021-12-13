import React, {useState} from "react";
import Notification from "./Notification";
import "./Confirmation.css";

const Confirmation = ({type, message, accept, decline}) => {
  let [notRender, setNotRender] = useState(false);
  
  const handleYes = (e) => {
    accept(e);
    setNotRender(true);
  }
  
   const handleNo = (e) => {
    decline(e);
    setNotRender(true);
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
  
  return !notRender? <Notification message={message} type={type} children={buttons} /> : true;
};

export default Confirmation;
