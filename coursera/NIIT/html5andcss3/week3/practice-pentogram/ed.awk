BEGIN {
  FS="\\<\\>"
}

function getfont(line) {
  i1 = index(line, ">")
  font = substr(line, i1+1);
  i2 = index(font, "<")
  font = substr(font, 0, i2-1)
  return font
}

/<p>/{
  if ($0 ~ /Almost/) {
    gsub("<p>", "<p style=\"font-family: " font ";\">", $0)
  } else {
    font = getfont($0)
    class = font
    gsub(" ", "", class)
    class=tolower("font-" class)
    gsub("<p>", "<p class=\"" class "\">", $0)
  }
}

# print all line
{
  print
}
