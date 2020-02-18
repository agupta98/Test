# Test

The following project will calculate the reward points for a User.
Calculation is based on - For every dollar spend above 50 will fetch a point and above 100 will fetch 2 points
I built a rest service which will calculate based on json provided below. It is post request.
The profile used will be SPRING_PROFILES_ACTIVE=dev. Pass it as environment varibales
url - http://localhost:9001/rewards/calculate
Payload for Insomnia

[
	{
		"id": "123",
		"name": "Dhruv",
		"amount": 1000,
		"date": "2014-01-12"
	},
	{
		"id": "123",
		"name": "Dhruv",
		"amount": 60,
		"date": "2014-01-12"
	},
	{
		"id": "123",
		"name": "Dhruv",
		"amount": 70,
		"date": "2015-02-12"
	},
	{
		"id": "234",
		"name": "Kushal",
		"amount": 70,
		"date": "2015-02-12"
	},
	{
		"id": "234",
		"name": "Kushal",
		"amount": 100,
		"date": "2015-02-12"
	}
]

The response for it is

{
  "mapTotal": {
    "UserId 234": 70,
    "UserId 123": 1880
  },
  "monthWise": {
    "User Id 123 -> Month Value Is 2": 20,
    "User Id 234 -> Month Value Is 2": 70,
    "User Id 123 -> Month Value Is 1": 1860
  },
  "message": "Success"
}
