import React, {useState} from "react";
import Notification from "./Notification";

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
        <button className="btn btn-primary" onClick={handleYes} >
          Yes
      </button>
      <br />
      <button className="btn btn-danger" onClick={handleNo}>
        No
      </button>
    </>
  );
  
  return !notRender? <Notification message={message} type={type} children={buttons} /> : true;
};

export default Confirmation;
