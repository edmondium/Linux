#pragma once
class Rectangle
{
private:
	int width;
	int height;
public:
	Rectangle()
		:width{1}, height{1}
	{}
	Rectangle(int initial_width, int initial_height)
		:width{initial_width}, height{initial_height}
	{}
	int get_width()
	{
		return width;
	}
	int get_height()
	{
		return height;
	}
	void resize(int new_width, int new_height)
	{
		width = new_width;
		height = new_height;
	}
	int area();
};

