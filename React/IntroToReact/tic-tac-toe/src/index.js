import React from "react";
import ReactDOM from "react-dom";
import "./index.css";

function Square(props) {
  let classNameLocal;
  if(props.classValue){
    classNameLocal = "square " + props.classValue;
  } else {
    classNameLocal = "square";
  }
  

  return (
    <button className={classNameLocal} onClick={props.onClick}>
      {props.value}
    </button>
  );
}

function isInclude(array , i){

  let answer = false;

  array.forEach(element => {
    if(element[0] === i[0] && element[1] === i[1]){
      answer = true;
    }
  });

  if(answer){
    return true;
  } else {
    return false;
  }
}

class Board extends React.Component {

  renderSquare(i) {
    const [i1, i2] = i;

    if(isInclude(this.props.winnerSet, i)){
      return (
        <Square
          value={this.props.squares[i1][i2]}
          classValue = {"square-winner"}
          onClick={() => this.props.onClick(i)}
        />
      );
    }
    return (
      <Square
        value={this.props.squares[i1][i2]}
        onClick={() => this.props.onClick(i)}
      />
    );
  }

  render() {
    const boardRowGenerator = (i) => {

      let boardRow = [];
      for(let j=0; j<3; j++){
        boardRow.push(this.renderSquare([i,  j]));
      }

      return (
        <div className="board-row">
          {boardRow}
        </div>
      );
    }

    let boardComplete = [];

    for(let i=0; i<3; i++){
      boardComplete.push(boardRowGenerator(i));
    }

    return (
      <div>
        {boardComplete}
      </div>
    );
  }
}

class Game extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      history: [
        {
          squares: [[null, null, null], [null, null, null], [null, null, null]]
        }
      ],
      lastPosition: [
        {
          position: [null, null]
        }
      ]
      ,
      stepNumber: 0,
      xIsNext: true,
    };
  }

  handleClick(i) {
    const history = this.state.history.slice(0,this.state.stepNumber + 1);
    const lastPosition = this.state.lastPosition.slice(0,this.state.stepNumber + 1);
    //const currentPosition = lastPosition[lastPosition.length - 1];
    const current = history[history.length - 1];
    const squares = current.squares.slice().map(i => i.slice());

    let [winner, ] = calculateWinner(squares);

    let [i1, i2] = i;

    if (winner || squares[i1][i2]) {
      return;
    }

    squares[i1][i2] = this.state.xIsNext ? "X" : "O";

    this.setState({
      history: history.concat([
        {
          squares: squares
        }
      ]),
      lastPosition: lastPosition.concat([
        {
          position: i
        }
      ]),
      stepNumber: history.length,
      xIsNext: !this.state.xIsNext,
    });
  }

  jumpTo(step) {
    this.setState({
      stepNumber: step,
      xIsNext: step % 2 === 0,
    });
  }



  render() {
    const history = this.state.history;
    const current = history[this.state.stepNumber];
    const [winner, moveWinner] = calculateWinner(current.squares);

    const moves = history.map((step, move) => {
      const desc = move ? "Go to move #" + move +" position (" + this.state.lastPosition[move].position +")" : "Go to game start";

      return (
        <li key={move}>
          <button onClick={() => this.jumpTo(move)}>{desc}</button>
        </li>
      );
    });

    let status;
    let winnerSet = [];

    if (winner) {
      status = (
        <div>
          <p>Congratulations to the Winner:</p>
          <p className="winner">Player: {winner}</p>
        </div>
      );
      winnerSet=moveWinner.slice();
    } else if (this.state.stepNumber === 9){
      status = (
        <div>
          <p className="tied">You Tied!</p>
        </div>
      )
    } else {
      status = "Next player: " + (this.state.xIsNext ? "X" : "O");
    }
    return (
      <div className="game">
        <div className="game-board">
        <Board
          squares={current.squares.slice()}
          winnerSet={winnerSet}
          onClick={(i) => this.handleClick(i)}
        />
          <ol>{moves}</ol>
        </div>
        <div className="game-info">
          <div>{status}</div>
        </div>
      </div>
    );
  }
}

function calculateWinner(squares) {

  const lines = [
    [[0,  0], [0,  1], [0,  2]],
    [[1,  0], [1,  1], [1,  2]],
    [[2,  0], [2,  1], [2,  2]],
    [[0,  0], [1,  0], [2,  0]],
    [[0,  1], [1,  1], [2,  1]],
    [[0,  2], [1,  2], [2,  2]],
    [[0,  0], [1,  1], [2,  2]],
    [[0,  2], [1,  1], [2,  0]],
  ];

  for (let j = 0; j < lines.length; j++) {
    const [ [a1, a2], [b1, b2],  [c1, c2]] = lines[j];
    
    if (squares[a1][a2] && squares[a1][a2] === squares[b1][b2] && squares[a1][a2] === squares[c1][c2]) {
      return [squares[a1][a2], lines[j]];
    }
  }

  return [null, null];
}

// ========================================

ReactDOM.render(<Game />, document.getElementById("root"));
