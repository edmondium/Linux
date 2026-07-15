dataframe <- read.csv("Social_Network_Ads.csv")
dataframe$User.ID <- NULL
dataframe[, 1:2] <- scale(dataframe[, 1:2])
library(caTools)
set.seed(123)
pecah <- sample.split(dataframe$Purchased,
                      SplitRatio = 0.7)
training_set <- subset(dataframe, pecah == TRUE)
test_set <- subset(dataframe, pecah == FALSE)
library(e1071)
classifier <- svm(formula = Purchased ~ .,
                  data = training_set,
                  type = "C-classification",
                  kernel = "linear")
y_pred <- predict(classifier, newdata = test_set[-3])
result <- cbind(test_set, y_pred)
