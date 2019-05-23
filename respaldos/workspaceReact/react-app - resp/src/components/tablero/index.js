import React, { Component } from 'react';
import ReactEcharts from 'echarts-for-react';
import data from './../../resources/data'


export default class Tablero extends Component {
    constructor(props){
        super(props)
        this.state = {
            chartName: data.name,
            cards: data.cards,
            lists: data.lists
        };
    }
    getSerieNames(){
        var series = []
        var values = []
        for(let id in this.state.lists){
            series[id] = this.state.lists[id].name

            let cardsNum = 0
            for(let idx in this.state.cards){
                if(this.state.lists[id].id === this.state.cards[idx].idList && (this.state.cards[idx].name !== "" || this.state.cards[idx].name !== undefined) ){
                    cardsNum++
                    console.log("listId:"+ this.state.lists[id].id+ " cardIdList:"+this.state.cards[idx].idList) 
                    console.log(this.state.lists[id].name)
                    console.log(this.state.cards[idx].name)
                    console.log(this.state.lists[id])
                    console.log(this.state.cards[idx])
                    //alert(0)
                } else {
                    console.log("NE")
                }
            }
            values[id] = cardsNum
        }
        return { "series": series, "values": values}
    }
    componentDidMount() {
        console.log(data)    
        
    }
    componentWillMount() {
        console.log(this.state)
    }
    render() {

        console.log(data)

        const option = {
            title: {
                text: data.name
            },
            tooltip: {},
            xAxis: {
                //data: ["A", "B", "C", "D", "E", "F"]
                data: this.getSerieNames().series
            },
            yAxis: {},
            series: [{
                name: 'Serie',
                type: 'bar',
                //data: [5, 20, 36, 10, 10, 20]
                data: this.getSerieNames().values
            }]
        };

        const onEvents = {
            'click': function (params) {
                // the 'this' variable can get echarts instance
                console.log(params);
            }
        };

        return (
            <ReactEcharts option={option} onEvents={onEvents} />
        );
    }
}

