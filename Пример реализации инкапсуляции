class Car:
    def __init__(self, brand, model, year):
        self.brand = brand
        self.model = model
        self.year = year

    def set_speed(self, speed):
        self.speed = speed

    def accelerate(self):
        if self.speed < 100:
            self.speed += 5
        else:
            print("Maximum speed reached")

    def brake(self):
        self.speed -= 5

    @property
    def get_speed(self):  # getter method for speed
        return self.speed

my_car = Car("BMW", "320i", 2018)
my_car.accelerate()
print(my_car.get_speed)
my_car
