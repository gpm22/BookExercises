import React, { useState } from "react";
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
      if (!stages.includes(stageInput.trim())) {
        let modifier = [...stages, stageInput.trim()];
        setStages([...modifier]);
        setStageInput("");
      }
    }
  };

  return (
    <div id="challenge-3-block" class="block">
      <Header />
      <main>
        <h1>Challenge 3 - Assembly Line</h1>
        <section className="description">
          <h4>Creates stages of an Assembly Line flowchart and then creates items to be added to this flowchart.</h4>
          <h4>Left click on the item will move it to the next stage at right.</h4>
          <h4>Right click on the item will move it to the previous stage at left.</h4>
        </section>
        Add an stage:{" "}
        <input
          value={stageInput}
          onKeyPress={handleKeyDown}
          onChange={(e) => {
            setStageInput(e.target.value);
          }}
          type="text"
        />
        <br />
        <AssemblyLine stages={stages} />
      </main>
      <Footer />
    </div>
  );
};

export default Challenge3;
