function ret = cd1(rbm_w, visible_data)
% <rbm_w> is a matrix of size <number of hidden units> by <number of visible units>
% <visible_data> is a (possibly but not necessarily binary) matrix of size <number of visible units> by <number of data cases>
% The returned value is the gradient approximation produced by CD-1. It's of the same shape as <rbm_w>.

  ## for problem 9
  visible_data = sample_bernoulli(visible_data);

  vthp = visible_state_to_hidden_probabilities(rbm_w, visible_data);
  hidden_sample = sample_bernoulli(vthp);

  htvp = hidden_state_to_visible_probabilities(rbm_w, hidden_sample);
  second_visible_sample = sample_bernoulli(htvp);

  rthp = visible_state_to_hidden_probabilities(rbm_w, second_visible_sample);

  ## for problem 7
  ## second_hidden_sample = sample_bernoulli(rthp); ## 100x1
  ## problem_7_result = ((hidden_sample * visible_data') - (second_hidden_sample * second_visible_sample')) / size(visible_data, 2);

  ## for problem 8
  problem_8_result = ((hidden_sample * visible_data') - (rthp * second_visible_sample')) / size(visible_data, 2);

  ret = problem_8_result;

end
