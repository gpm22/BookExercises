import React, { useState } from "react";
import Header from "../commons/Header";
import Footer from "../commons/Footer";

const Highlighter = () => {
  let [sourceText, setSourceText] = useState("");
  let [searchTerm, setSearchTerm] = useState("");
  let [isCaseSensitive, setIsCaseSensitive] = useState(false);

  const resultText = () => {
    console.log("isCaseSensitive: " + isCaseSensitive);
    let sourceInsideText = typeof sourceText === "string" ? sourceText : "-";
    let searchInsideTerm = isCaseSensitive
      ? searchTerm
      : new RegExp(searchTerm, "ig");

    return searchTerm === ""
      ? sourceInsideText
      : sourceInsideText
        .split(" ")
        .map((word) =>
          word.replace(searchInsideTerm, (i) => "<mark>" + i + "</mark>")
        )
        .join(" ");
  };

  return (
    <div id="challenge-2-block" class="block">
      <Header />
      <main>
        <h1>Challenge 2 - Highlighter</h1>
        <section className="description">
          <h4>Highlights a specific term in a given text.</h4>
        </section>
        <p>Enter the text:</p>
        <textarea
          value={sourceText}
          onChange={(e) => setSourceText(e.target.value)}
          className="source-text"
          name="source-text"
          rows="5"
          cols="30"
        />
        <br />
        <br />
        Enter the search term:
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="search-term"
        />
        <br />
        Case Sentive?
        <input
          onChange={() => {
            setIsCaseSensitive(!isCaseSensitive);
          }}
          type="checkbox"
          className="case-sensitive"
          checked={isCaseSensitive}
        />
        <br />
        <h4>Result</h4>
        <div
          className="result"
          dangerouslySetInnerHTML={{ __html: resultText() }}
        ></div>
      </main>
      <Footer />
    </div>
  );
};

export default Highlighter;
