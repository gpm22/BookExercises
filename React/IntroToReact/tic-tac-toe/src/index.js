import React from "react";
import ReactDOM from "react-dom";
import "./index.css";

const squareSide = 3;

function Square(props) {
  let classNameLocal;
  if (props.classValue) {
    classNameLocal = "square " + props.classValue;
  } else {
    classNameLocal = "square";
  }

  return (
    <button className={classNameLocal} onClick={props.onClick}>
      <p class="square-position">
        ({props.location[0]}, {props.location[1]})
      </p>
      {props.value}
    </button>
  );
}

function isInclude(array, i) {
  let answer = false;

  array.forEach((element) => {
    if (element[0] === i[0] && element[1] === i[1]) {
      answer = true;
    }
  });

  return answer;
}

function squareGenerator() {
  let square = [];

  for (let i = 0; i < squareSide; i++) {
    square.push([]);
    for (let j = 0; j < squareSide; j++) {
      square[i].push(null);
    }
  }

  return square;
}

class Board extends React.Component {
  renderSquare(i) {
    const [i1, i2] = i;

    if (isInclude(this.props.winnerSet, i)) {
      return (
        <Square
          value={this.props.squares[i1][i2]}
          classValue={"square-winner"}
          onClick={() => this.props.onClick(i)}
          location={i}
        />
      );
    }
    return (
      <Square
        value={this.props.squares[i1][i2]}
        onClick={() => this.props.onClick(i)}
        location={i}
      />
    );
  }

  render() {
    const boardRowGenerator = (i) => {
      let boardRow = [];
      for (let j = 0; j < squareSide; j++) {
        boardRow.push(this.renderSquare([i, j]));
      }

      return <div className="board-row">{boardRow}</div>;
    };

    let boardComplete = [];

    for (let i = 0; i < squareSide; i++) {
      boardComplete.push(boardRowGenerator(i));
    }

    return <div>{boardComplete}</div>;
  }
}

function changeColor(step) {
  try {
    const allLi = document.getElementsByClassName("li-hist");

    for (let i = 0; i < allLi.length; i++) {
      allLi[i].style.backgroundColor = "#eee";
      allLi[i].style.color = "#000";
    }

    const li = document.getElementById("li-hist-" + step);

    li.style.backgroundColor = "green";
    li.style.color = "#eee";

  } catch (e) {}
}

class Game extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      history: [
        {
          squares: squareGenerator(),
        },
      ],
      lastPosition: [
        {
          position: [null, null],
        },
      ],
      stepNumber: 0,
      xIsNext: true,
      order: true,
    };
  }

  handleClick(i) {
    const history = this.state.history.slice(0, this.state.stepNumber + 1);
    const lastPosition = this.state.lastPosition.slice(
      0,
      this.state.stepNumber + 1
    );
    const current = history[history.length - 1];
    const squares = current.squares.slice().map((i) => i.slice());

    let [winner] = calculateWinner(squares);

    let [i1, i2] = i;

    if (winner || squares[i1][i2]) {
      return;
    }

    squares[i1][i2] = this.state.xIsNext ? "X" : "O";

    this.setState({
      history: history.concat([
        {
          squares: squares,
        },
      ]),
      lastPosition: lastPosition.concat([
        {
          position: i,
        },
      ]),
      stepNumber: history.length,
      xIsNext: !this.state.xIsNext,
    });
  }

  jumpTo(step) {
    changeColor(step);
    this.setState({
      stepNumber: step,
      xIsNext: step % 2 === 0,
    });
  }

  reorder() {
    this.setState({
      order: !this.state.order,
    });
  }

  render() {
    const history = this.state.history;
    const current = history[this.state.stepNumber];
    const lastSquares = history[history.length - 1].squares;
    const [winner, moveWinner] = calculateWinner(current.squares);

    const nomeClasse = (i) => {
      return "li-hist-" + i;
    };

    changeColor(this.state.stepNumber);

    const moves = history.map((step, move) => {
      const desc = move
        ? "Go to move #" +
          move +
          " - " +
          lastSquares[this.state.lastPosition[move].position[0]][
            this.state.lastPosition[move].position[1]
          ] +
          " in position (" +
          this.state.lastPosition[move].position +
          ")"
        : "Go to game start";

      if (move < history.length - 1) {
        return (
          <li key={move}>
            <button
              className="li-hist"
              id={nomeClasse(move)}
              onClick={() => this.jumpTo(move)}
            >
              {desc}
            </button>
          </li>
        );
      } else {
        return (
          <li key={move}>
            <button
              className="li-hist li-hist-last"
              id={nomeClasse(move)}
              onClick={() => this.jumpTo(move)}
            >
              {desc}
            </button>
          </li>
        );
      }
    });

    if (!this.state.order) {
      moves.reverse();
    }

    let status;
    let winnerSet = [];

    if (winner) {
      status = (
        <div>
          <p>Congratulations to the Winner:</p>
          <p className="winner">Player: {winner}</p>
        </div>
      );
      winnerSet = moveWinner.slice();
    } else if (this.state.stepNumber === squareSide * squareSide) {
      status = (
        <div>
          <p className="tied">You Tied!</p>
        </div>
      );
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

          <ul>
            <button onClick={() => this.reorder()}>Reorder List</button>
            {moves}
          </ul>
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
    [
      [0, 0],
      [0, 1],
      [0, 2],
    ],
    [
      [1, 0],
      [1, 1],
      [1, 2],
    ],
    [
      [2, 0],
      [2, 1],
      [2, 2],
    ],
    [
      [0, 0],
      [1, 0],
      [2, 0],
    ],
    [
      [0, 1],
      [1, 1],
      [2, 1],
    ],
    [
      [0, 2],
      [1, 2],
      [2, 2],
    ],
    [
      [0, 0],
      [1, 1],
      [2, 2],
    ],
    [
      [0, 2],
      [1, 1],
      [2, 0],
    ],
  ];

  for (let j = 0; j < lines.length; j++) {
    const [[a1, a2], [b1, b2], [c1, c2]] = lines[j];

    if (
      squares[a1][a2] &&
      squares[a1][a2] === squares[b1][b2] &&
      squares[a1][a2] === squares[c1][c2]
    ) {
      return [squares[a1][a2], lines[j]];
    }
  }

  return [null, null];
}

// ========================================

ReactDOM.render(<Game />, document.getElementById("root"));
