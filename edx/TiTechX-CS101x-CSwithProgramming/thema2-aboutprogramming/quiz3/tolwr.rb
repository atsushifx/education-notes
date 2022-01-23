## 'A' -> 'a'

ss = gets().chomp()
leng = ss.length
aa = ss.unpack("C*")
tt = Array.new(leng)

code_a = 97
code_A = 65
for i in 0..(leng-1)
  sa = aa[i] - code_A
  if (0<=sa && sa<=26-1) then
    sa = sa + (code_a - code_A)
  end
  tt[i] = sa + code_A
end
bb = tt.pack("C*")
puts(bb)
