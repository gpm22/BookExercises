def parametrize(func, *default_args, **default_kwargs):
  """
  This function creates a new function that wraps the given function and applies the provided default keyword arguments.

  Args:
      func: The function to be wrapped.
      **default_kwargs: Keyword arguments to be used as defaults when calling the wrapped function.

  Returns:
      A new function that wraps the original function and applies the default keyword arguments.
  """

  def wrapper(*args, **kwargs):
    # Merge the positional keyword arguments with the provided positional arguments.
    args = args + default_args

    # Merge the default keyword arguments with the provided keyword arguments.
    merged_kwargs = { **default_kwargs, **kwargs }

    # Call the original function with the merged keyword arguments.
    return func(*args, **merged_kwargs)

  return wrapper
