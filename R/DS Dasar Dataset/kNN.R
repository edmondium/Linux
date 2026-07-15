dataframe <- read.csv("Social_Network_Ads.csv")
dataframe$User.ID <- NULL
dataframe[, 1:2] <- scale(dataframe[, 1:2])
library(caTools)
set.seed(123)
pecah <- sample.split(dataframe$Purchased,
                      SplitRatio = 0.7)
training_set <- subset(dataframe, pecah == TRUE)
test_set <- subset(dataframe, pecah == FALSE)
library(class)
y_pred <- knn(train = training_set[, -3],
              test = test_set[, -3],
              cl = training_set[, 3],
              k = 5)
summary(y_pred)
result <- cbind(test_set, y_pred)
