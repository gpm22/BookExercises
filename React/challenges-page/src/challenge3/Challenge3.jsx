import React, {useState} from "react";
import AssemblyLine from "./AssemblyLine";
import Header from "../commons/Header";
import Footer from "../commons/Footer";

/* Changes made to this file will not affect your tests.
 * This file is used to control the behavior of the web preview.
 */
const Challenge3 = (props) => {

  let [stages, setStages] = useState([]);
  let [stageInput, setStageInput] = useState("");

  const handleKeyDown = (event) => {
    if (event.key === 'Enter' && stageInput !== "") {
      let modifier = [...stages, stageInput.trim()];
      setStages([...modifier]);
      setStageInput("");
    }
  };

  return (
    <div id="challenge-3-block" class="block">
      <Header />
      <main>
        <h1>Challenge 3 - Assembly Line</h1>
        Add an stage:{" "}
      <input
        value={stageInput}
        onKeyPress={handleKeyDown}
        onChange={(e) => {
          setStageInput(e.target.value);
        }}
        type="text"
      />
      <br/>
        <AssemblyLine stages={stages} />
      </main>
      <Footer />
    </div>
  );
};

export default Challenge3;
