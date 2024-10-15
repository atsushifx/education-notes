# divloop.rb
# input: d
# output: calc 1/d with loop:

puts("input d (calc 1/d: d >= 2)\n")
d = gets().to_i
print("calc  1 / ", d, " \n")
t = Array.new(d, 0)

stop = 0
x = 1
t[0] = 1 # guard
while stop != 1
  x = x * 10
  q = x / d
  x = x % d
  if (t[x] != 0)
    stop = 1
  else
    print(q, ", ", x, "\n");
    sleep(0.5)
  end
  t[x]=1
end
