package main
import (
"io/ioutil"
)

func check(e error) {
    if e != nil {
        panic(e)
    }
}

func main() {
    d1 := []byte("hello\ngo\nFrom PHP script! \nhehe\n")
    err := ioutil.WriteFile("twitterJson", d1, 0644)
    check(err)
//    fmt.Println("hello world")
}
