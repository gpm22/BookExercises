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
class Board extends React.Component {

  renderSquare(i) {

    if(this.props.winnerSet.includes(i)){
      return (
        <Square
          value={this.props.squares[i]}
          classValue = {"square-winner"}
          onClick={() => this.props.onClick(i)}
        />
      );
    }
    return (
      <Square
        value={this.props.squares[i]}
        onClick={() => this.props.onClick(i)}
      />
    );
  }

  render() {
    return (
      <div>
        <div className="board-row">
          {this.renderSquare((0, 0))}
          {this.renderSquare((0, 1))}
          {this.renderSquare((0, 2))}
        </div>
        <div className="board-row">
          {this.renderSquare((1, 0))}
          {this.renderSquare((1, 1))}
          {this.renderSquare((1, 2))}
        </div>
        <div className="board-row">
          {this.renderSquare((2, 0))}
          {this.renderSquare((2, 1))}
          {this.renderSquare((2, 2))}
        </div>
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
          squares: Array(9).fill(null),
        },
      ],
      stepNumber: 0,
      xIsNext: true,
    };
  }

  handleClick(i) {
    const history = this.state.history.slice(0, this.state.stepNumber + 1);
    const current = history[history.length - 1];
    const squares = current.squares.slice();
    let [winner, ] = calculateWinner(squares);

    if (winner || squares[i]) {
      return;
    }

    squares[i] = this.state.xIsNext ? "X" : "O";

    this.setState({
      history: history.concat([
        {
          squares: squares,
        },
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
      const desc = move ? "Go to move #" + move : "Go to game start";

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
          squares={current.squares}
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
  /*const lines = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
  ];*/

  const lines = [
    [(0, 0), (0, 1), (0, 2)],
    [(1, 0), (1, 1), (1, 2)],
    [(2, 0), (2, 1), (2, 2)],
    [(0, 0), (1, 0), (2, 0)],
    [(0, 1), (1, 1), (2 ,1)],
    [(0, 2), (1, 2), (2, 2)],
    [(0, 0), (1, 1), (2, 2)],
    [(0, 2), (1, 1), (2, 0)],
  ];

  for (let j = 0; j < lines.length; j++) {
    const [a, b, c] = lines[j];
    if (squares[a] && squares[a] === squares[b] && squares[a] === squares[c]) {
      return [squares[a], lines[j]];
    }
  }

  return [null, null];
}

// ========================================

ReactDOM.render(<Game />, document.getElementById("root"));
