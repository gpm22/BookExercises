import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import Challenge1 from "./challenge1/Challenge1";
import Highlighter from "./challenge2/Highlighter";
import Challenge3 from "./challenge3/Challenge3";

ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="challenge-1" element={<Challenge1 />} />
      <Route path="challenge-2" element={<Highlighter />} />
      <Route path="challenge-3" element={<Challenge3 />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
