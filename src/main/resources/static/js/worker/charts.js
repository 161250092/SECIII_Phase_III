var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '环形图';

option = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        // data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
        data:['完成','未完成']
    },
    series: [
        {
            name:'完成情况',
            type:'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:finished, name:'完成'},
                {value:unfinished, name:'未完成'},

            ]
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
