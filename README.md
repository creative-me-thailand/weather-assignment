# Creative Me :)

สวัสดี! ขอบคุณที่ให้ความสนใจกับบริษัทเรา **Creative Me**
ก่อนที่เราจะได้ร่วมงานกัน มีโจทย์ง่ายๆ หนึ่ง assignment ให้คุณทำ 

![Geek](https://media.giphy.com/media/ZVik7pBtu9dNS/giphy.gif)


## Rick and Morty Application
สร้างแอพพลิเคชั่นด้วยภาษาตามสายงานที่คุณสมัครเข้ามาทำงาน เช่น
 - iOS: Swift
 - Android: Kotlin, Java
 - Website Frontend: HTML + JavaScript (with framework like React, VueJS)
 
เชื่อมต่อแอพเข้ากับ [Rick and Morty API - https://rickandmortyapi.com ](https://rickandmortyapi.com/)
เพื่อแสดงผล UI รายชื่อของตัวละครต่างๆในเรื่อง โดยหน้าแรกให้แสดงรายชื่อซึ่งดึงจากเส้น
```text
https://rickandmortyapi.com/api/character/
```
นำออกมาแสดงผลเป็น List  แสดงชื่อและรูปภาพ และสามารถกดที่ List เพื่อ navigate ไปหน้าต่อไปสำหรับแสดงรายละเอียด Location ของตัวละครนั้นๆด้วย สามารถดึงข้อมูลจาก api ได้จาก url ใน json ได้เลย

> Response Example

```json
{
  "info": {
    "count": 394,
    "pages": 20,
    "next": "https://rickandmortyapi.com/api/character/?page=20",
    "prev": "https://rickandmortyapi.com/api/character/?page=18"
  },
  "results": [
    {
      "id": 361,
      "name": "Toxic Rick",
      "status": "Dead",
      "species": "Humanoid",
      "type": "Rick's Toxic Side",
      "gender": "Male",
      "origin": {
        "name": "Alien Spa",
        "url": "https://rickandmortyapi.com/api/location/64"
      },
      "location": {
        "name": "Earth",
        "url": "https://rickandmortyapi.com/api/location/20"
      },
      "image": "https://rickandmortyapi.com/api/character/avatar/361.jpeg",
      "episode": [
        "https://rickandmortyapi.com/api/episode/27"
      ],
      "url": "https://rickandmortyapi.com/api/character/361",
      "created": "2018-01-10T18:20:41.703Z"
    }
  ]
}
```

### BONUS POINT!
 - [X] Unit Test
 - [X] Design Pattern
 - [X] Tools
 - [X] Architecture Design pattern (MVC, MVP, MVVM, Clean Architecture, Redux and more)
 - [X] Clean Code

## How to deliver ?
ส่งผลงานได้โดย Fork repository นี้ แล้ว pull request หรือถ้าหากไม่ถนัด สามารถ compress แล้วส่งมาให้ทาง hr.creativeme@gmail.com ได้เลยจ้าา

### หวังว่าจะได้ร่วมงานกันนะทุกคน ;)

![HOPE TO SEE YA ALL !](https://media.giphy.com/media/3ornk7TgUdhjhTYgta/giphy.gif)

