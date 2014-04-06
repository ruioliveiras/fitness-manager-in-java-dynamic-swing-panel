{
    users:[
        '{{repeat(2000)}}', /*Mudar tambem no muneric de friends, pode adicionar 2 amigos iguas*/
        {
            id: '{{index()}}',
			email: '{{email()}}',
            pass: 'userpass',
			name: '{{firstName()}} {{surname()}}',
            gender: '{{gender()}}',
            height: '{{numeric(150,190)}}',
            weight: '{{numeric(60,100)}}',
            born: '{{date(new Date(numeric(1950,2004), 0, 1),new Date(), "YYYY-MM-dd")}}',
			friends: [
            '{{repeat(3,7)}}',
            {
                id: '{{numeric(0,2000)}}'
          
            }],
            exercises:[
            '{{repeat(3,7)}}',
            {
				id: '{{numeric(0,3)}}',
				date: '{{date(new Date(numeric(2012,2014), 0, 1),new Date(), "YYYY-MM-dd")}}'
               
            }]
            
		}
    ]
}