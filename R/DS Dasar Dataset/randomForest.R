dataframe <- read.csv("Social_Network_Ads.csv")
dataframe$User.ID <- NULL
dataframe$Purchased <- as.factor(dataframe$Purchased)
library(caTools)
set.seed(123)
pecah <- sample.split(dataframe$Purchased,
                      SplitRatio = 0.7)
training_set <- subset(dataframe, pecah == TRUE)
test_set <- subset(dataframe, pecah == FALSE)
library(randomForest)
classifier <- randomForest(x = training_set[-3],
                           y = training_set$Purchased,
                           ntree = 10)
classifier
y_pred <- predict(classifier, newdata = test_set[-3])
result <- cbind(test_set, y_pred)
