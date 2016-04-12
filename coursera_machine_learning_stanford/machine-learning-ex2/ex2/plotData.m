function plotData(X, y)
%PLOTDATA Plots the data points X and y into a new figure 
%   PLOTDATA(x,y) plots the data points with + for the positive examples
%   and o for the negative examples. X is assumed to be a Mx2 matrix.

% Create New Figure
figure; 
hold on;

% ====================== YOUR CODE HERE ======================
% Instructions: Plot the positive and negative examples on a
%               2D plot, using the option 'k+' for the positive
%               examples and 'ko' for the negative examples.
%

% isPos = logical(y);
% posEx = X(isPos,:);
% negEx = X(!isPos,:);
posEx = X(find(y==1),:);
negEx = X(find(y==0),:);

% figure;
% hold on;
plot(posEx(:,1),posEx(:,2), 'k+', 'color', 'b');
plot(negEx(:,1),negEx(:,2), 'ko', 'color', 'r');

% =========================================================================

hold off;

end
