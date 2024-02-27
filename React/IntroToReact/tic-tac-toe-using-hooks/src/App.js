import { useState } from "react";

function Square({value, onClick}){
  return <button className="square" onClick={onClick}>{value}</button>;
}

function calculateWinner(squares){
  const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6]
  ]

  for (let line of lines){
    const [a, b, c] = line;

    if(squares[a] && squares[a] === squares[b] && squares[a] === squares[c]){
      return squares[a];
    }
  }

  return null;
}

function Board({xIsNext, squares, onPlay}) {

  function handleClick(i)   {

    if(squares[i] || calculateWinner(squares)){
      return;
    }

    const nextSquares = squares.slice();
    nextSquares[i] = xIsNext ? "X" : "O";
    onPlay(nextSquares)
  }

  const winner = calculateWinner(squares);
  let status = winner ? "Winner: " + winner : "Next player: " + (xIsNext? "X" : "O");

  return (
    <>
      <div className="status">{status}</div>
      <div className="board-row">
        <Square value = {squares[0]} onClick = {() => handleClick(0)} />
        <Square value = {squares[1]} onClick = {() => handleClick(1)} />
        <Square value = {squares[2]} onClick = {() => handleClick(2)} />
      </div>
      <div className="board-row">
        <Square value = {squares[3]} onClick = {() => handleClick(3)} />
        <Square value = {squares[4]} onClick = {() => handleClick(4)} />
        <Square value = {squares[5]} onClick = {() => handleClick(5)} />
      </div>      
      <div className="board-row">
        <Square value = {squares[6]} onClick = {() => handleClick(6)} />
        <Square value = {squares[7]} onClick = {() => handleClick(7)} />
        <Square value = {squares[8]} onClick = {() => handleClick(8)} />
      </div>
    </>
);

}

export default function Game(){
  const [history, setHistory] = useState([Array(9).fill(null)]);
  const [currentMove, setCurrentMove] = useState(0);

  const xIsNext = currentMove % 2 === 0;
  const currentSquares = history[currentMove];

  function handlePlay(nextSquares){
    const nextHistory = [...history.slice(0, currentMove+1), nextSquares];
    setHistory(nextHistory);
    setCurrentMove(nextHistory.length - 1);
  }

  const moves = history.map((squares, move) => {
    let description = move > 0 ? "Go to move #" + move : "Go to game start";

    return (
      <li key={move}>
        {move != currentMove ?
          <button onClick={() => setCurrentMove(move)}>{description}</button> :
          "You are at move #" + move}
      </li>
    );
  });

  return (
    <div className="game">
      <div className="game-board">
        <Board xIsNext={xIsNext} squares={currentSquares} onPlay ={handlePlay} />
      </div>
      <div className="game-info">
        <ol>{moves}</ol>
      </div>
    </div>
  )
}
