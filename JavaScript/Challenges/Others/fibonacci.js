function fibonacci_recursive(n) {
  if (n < 0) {
    return;
  }

  if (n === 0) {
    return 0;
  }

  if (n === 1) {
    return 1;
  }

  return fibonacci_recursive(n - 1) + fibonacci_recursive(n - 2);
}

console.log(
  "fibonacci_recursive(10) = 55 ? " +
    (fibonacci_recursive(10) === 55 ? "Yes" : "No")
);
console.log(
  "fibonacci_recursive(17) = 1597 ? " +
    (fibonacci_recursive(17) === 1597 ? "Yes" : "No")
);

function fibonacci_iterative(n) {
  if (n < 0) {
    return;
  }

  if (n === 0) {
    return 0;
  }

  if (n === 1) {
    return 1;
  }

  let n_2 = 0;
  let n_1 = 1;
  let actual = 0;

  for (let i = 2; i <= n; i++) {
    actual = n_1 + n_2;
    n_2 = n_1;
    n_1 = actual;
  }

  return actual;
}

console.log(
  "fibonacci_iterative(10) = 55 ? " +
    (fibonacci_iterative(10) === 55 ? "Yes" : "No")
);
console.log(
  "fibonacci_iterative(17) = 1597 ? " +
    (fibonacci_iterative(17) === 1597 ? "Yes" : "No")
);
