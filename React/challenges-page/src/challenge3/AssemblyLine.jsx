import React, { useState, useEffect } from "react";
import "./AssemblyLine.css";

const AssemblyLine = (props) => {
  let [itensInput, setItensInput] = useState("");

  let teste = ["Idea", "Development", "Testing", "Deployment"];

  let stagesNames = props.stages ? props.stages : teste;

  const columnsInitiator = {};

  const [columns, setColumns] = useState(columnsInitiator);

  useEffect(() => {
    stagesNames.forEach((stage) => columnsInitiator[stage] = []);
    setColumns(columnsInitiator);
  }, [props.stages]);

  console.log(columns);

  const handleContextMenu = (e) => {
    e.preventDefault();
    e.stopPropagation();
    move(e, -1);
  }

  const handleStageClick = (e) => {
    e.preventDefault();
    move(e, 1);
  };

  const move = (e, direction) => {

    let modifier = { ...columns };
    let next = direction > 0 ? stagesNames.indexOf(e.target.title) + 1 
                             : stagesNames.indexOf(e.target.title) - 1;

    if (next > -1 && next < stagesNames.length) {
      modifier[stagesNames[next]] = [...columns[stagesNames[next]], e.target.value];
    }

    remove(e, modifier);
  }

  const remove = (e, modifier) => {
    let copy = [...columns[e.target.title]];

    copy.splice(copy.indexOf(e.target.value), 1);

    modifier[e.target.title] = [...copy];

    setColumns({ ...modifier });
  };

  const Stages = () =>
    stagesNames.map((stage) => (
      <section key={stage} id={stage} className="assembly-stage">
        <h1 key={stage} className="title">{stage}</h1>
        {columns[stage]? columns[stage].map((value) => (
          <button key={value} onClick={(e) => handleStageClick(e)} onContextMenu={handleContextMenu} title={stage} className="assembly-item" value={value}>
            {value}
          </button>
        )): null}
      </section>
    ));

  const handleKeyDown = (event) => {
    if (event.key === 'Enter' && itensInput !== "") {
      let modifier = { ...columns };
      modifier[stagesNames[0]] = [itensInput.trim(), ...columns[stagesNames[0]]];
      setColumns({ ...modifier });
      setItensInput("");
    }
  };

  return (
    <>
      Add an item:{" "}
      <input
        className="assembly-add-item"
        value={itensInput}
        onKeyPress={handleKeyDown}
        onChange={(e) => {
          setItensInput(e.target.value);
        }}
        type="text"
      />
      <section id="kanban">
        <Stages />
      </section>
    </>
  );
};

export default AssemblyLine;
