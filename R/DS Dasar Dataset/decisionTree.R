dataframe <- read.csv("Social_Network_Ads.csv")
dataframe$User.ID <- NULL
library(caTools)
set.seed(123)
pecah <- sample.split(dataframe$Purchased,
                      SplitRatio = 0.7)
training_set <- subset(dataframe, pecah == TRUE)
test_set <- subset(dataframe, pecah == FALSE)
library(rpart)
classifier <- rpart(formula = as.factor(Purchased) ~ .,
                    data = training_set)
classifier
y_pred <- predict(classifier,
                  newdata = test_set[-3],
                  type = "class")
result <- cbind(test_set, y_pred)
