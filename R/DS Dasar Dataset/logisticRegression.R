dataframe <- read.csv("Social_Network_Ads.csv")
dataframe$User.ID <- NULL
dataframe[, 1:2] <- scale(dataframe[, 1:2])
library(caTools)
set.seed(123)
pecah <- sample.split(dataframe$Purchased,
                      SplitRatio = 0.7)
training_set <- subset(dataframe, pecah == TRUE)
test_set <- subset(dataframe, pecah == FALSE)
classifier <- glm(formula = Purchased ~ .,
                  family = binomial, data = training_set)
summary(classifier)
prob_pred <- predict(classifier, type = "response",
                     newdata = test_set[-3])
prob_pred
y_pred <- ifelse(prob_pred >= 0.5, 1, 0)
result <- cbind(test_set, y_pred)
