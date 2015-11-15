var NEW_QUESTION = {

    questionCorrect: false,
    myQuestion: null,
    numAnswers: 0,
    mcAnswer: "",

    Question: function () {
        this.id = 0;
        this.source_id = 0;
        this.correct = "";
        this.img_path = "";
        this.text = "";
        this.type = "";
        this.responses = [];
    },

    init: function() {
        NEW_QUESTION.myQuestion = new this.Question();
        NEW_QUESTION.myQuestion.id = $('#question-id').text();
        NEW_QUESTION.myQuestion.source_id = $('#question-source').text();
        NEW_QUESTION.myQuestion.text = $('#question-text').text();
        NEW_QUESTION.myQuestion.correct = $('#question-correct').text();
        NEW_QUESTION.myQuestion.img_path = $('#question-img-path').text();
        NEW_QUESTION.myQuestion.before_image = $('#question-before').text();
        if (NEW_QUESTION.myQuestion.before_image.indexOf(".JPG") == -1) {
        	NEW_QUESTION.myQuestion.before_image.concat(".JPG");
        }
        NEW_QUESTION.myQuestion.after_image = $('#question-after').text();
        if (NEW_QUESTION.myQuestion.after_image.indexOf(".JPG") == -1) {
        	NEW_QUESTION.myQuestion.after_image.concat(".JPG");
        }
        NEW_QUESTION.myQuestion.type = $('#question-type').text();
        for (var i = 1; i <= 6; i++) {
        	var resp = {
        			"correct_answer": $('#question-answer' + i).text(),
        			"student_correct": false
        	};
        	NEW_QUESTION.myQuestion.responses.push(resp);
        }
        NEW_QUESTION.show_before_image();
    },

    show_before_image: function() {
        $('#illustration').empty();
        if (NEW_QUESTION.myQuestion.before_image.length > 0) {
            $('#illustration').append(
                '<div class="col-sm-6" id="image_div">'
                    + '<img src="'
                    + NEW_QUESTION.myQuestion.img_path
                    + NEW_QUESTION.myQuestion.before_image
                    + '" align="center">' +
                '</div>');
        }
    },

    show_after_image: function() {
        $('#illustration').empty();
        if (NEW_QUESTION.myQuestion.after_image.length > 0) {
            $('#illustration').append(
                '<div class="col-sm-6" id="image_div">'
                    + '<img src="'
                    + NEW_QUESTION.myQuestion.img_path
                    + NEW_QUESTION.myQuestion.after_image
                    + '" align="center">' +
                '</div>');
        }
    },

    set_correct: function(correct, key) {
        if (correct) {
            $(key).text("Your answer is correct!");
            NEW_QUESTION.questionCorrect = true;
            NEW_QUESTION.show_after_image();
        } else {
            $(key).text("Your answer is incorrect!");
            NEW_QUESTION.questionCorrect = false;
        }
    },

    update_dom: function() {

        if (NEW_QUESTION.myQuestion.type.indexOf("SA") == 0) {	// short answer
            $('#submit-answer-button').show();
            $('#see-answer-button').show();
            NEW_QUESTION.numAnswers = parseInt(
            		NEW_QUESTION.myQuestion.type.substring(3,
            				NEW_QUESTION.myQuestion.type.length));
                for (var i = 1; i <= NEW_QUESTION.numAnswers; i++) {
                        $('#form-elements').append(
                    '<div class="form-group">' +
                      '<label for="Answer' + i + 'input" ' +
                      'class="col-sm-2 control-label">Your Answer</label>' +
                      '<div class="col-sm-6">' +
                        '<input type="text" class="form-control" ' +
                        'id="Answer' + i + 'input" action="#">' +
                        '<div class="correct-text" ' +
                        'id="correct-text-' + i + '"></div>' +
                        '<div class="incorrect-text" ' +
                        'id="incorrect-text-' + i + '"></div>' +
                      '</div>' +
                    '</div>');
                };
        } else if (NEW_QUESTION.myQuestion.type == "TF") {	// true false
            $('#submit-answer-button').hide();
            $('#see-answer-button').hide();
            $('#form-elements').append(
                '<div class="form-group">' +
                  '<div class="col-sm-2"></div>' +
                  '<div class="col-sm-6">' +
                    '<div class="btn-group" role="group" aria-label="...">' +
                        '<button type="button" class="btn btn-default" ' +
                            'id="answer-true">True</button>' +
                        '<button type="button" class="btn btn-default" ' +
                            'id="answer-false">False</button>' +
                    '</div>' +
                    '<div class="correct-text" id="correct-text-tf"></div>' +
                    '<div class="incorrect-text" ' +
                        'id="incorrect-text-tf"></div>' +
                  '</div>' +
                '</div>');
            $('#answer-true').click( function () {
                $('#correct-text-tf').text("");
                $('#incorrect-text-tf').text("");
                if (NEW_QUESTION.myQuestion
                		.responses[0].correct_answer == "TRUE") {
                    NEW_QUESTION.set_correct(true, '#correct-text-tf');
                } else {
                    NEW_QUESTION.set_correct(false, '#incorrect-text-tf');
                }
            });
            $('#answer-false').click( function () {
                $('#correct-text-tf').text("");
                $('#incorrect-text-tf').text("");
                if (NEW_QUESTION.myQuestion
                		.responses[0].correct_answer == "FALSE") {
                    NEW_QUESTION.set_correct(true, '#correct-text-tf');
                } else {
                    NEW_QUESTION.set_correct(false, '#incorrect-text-tf');
                }
            });
        } else if (NEW_QUESTION.myQuestion.type == "MC") {	// multiple guess
            NEW_QUESTION.numAnswers = 3;
            for (var i = 1; i <= NEW_QUESTION.numAnswers; i++) {
                var qLetter = String.fromCharCode('A'.charCodeAt() + i - 1);
            $('#form-elements').append(
            '<div class="form-group">' +
              '<div class="col-sm-2"></div>' +
              '<div class="col-sm-6">' +
                //$('#form-elements').append(
                '<div class="radio">' +
                	'<label>' +
                    '<input type="radio" name="radio" value="' + qLetter + '">' +
                        NEW_QUESTION.myQuestion.responses[i-1].correct_answer +
                        '</label>' +
                '</div>' +
            //$('#form-elements').append(
                '<div class="correct-text" id="correct-text-mc-' + qLetter
                		+ '"></div>' +
                '<div class="incorrect-text" ' +
                        'id="incorrect-text-mc-' + qLetter + '"></div>' +
              '</div>' +
            '</div>');
            }
        }
    },

    answer_submit: function() {
        if (NEW_QUESTION.myQuestion.type.indexOf("SA") == 0) {
                // short answer
            NEW_QUESTION.numAnswers = parseInt(
                    NEW_QUESTION.myQuestion.type.substring(3,
                    NEW_QUESTION.myQuestion.type.length));
            NEW_QUESTION.questionCorrect = true;
            for (var i = 0; i < NEW_QUESTION.numAnswers; i++) {
                    var index = i + 1;
                $('#incorrect-text-' + index).text("");
                $('#correct-text-' + index).text("");
                if (NEW_QUESTION.myQuestion.responses[i].correct_answer
                        === $('#Answer'+ index +'input').val()) {
                    NEW_QUESTION.set_correct(true,
                        '#correct-text-' + index);
                } else {
                    NEW_QUESTION.set_correct(false,
                        '#incorrect-text-' + index);
                    NEW_QUESTION.questionCorrect = false;
                }
            }
        } else if (NEW_QUESTION.myQuestion.type === "MC") {
            NEW_QUESTION.questionCorrect = false;
            if (NEW_QUESTION.mcAnswer === NEW_QUESTION.myQuestion.correct) {
                NEW_QUESTION.set_correct(true, '#correct-text-mc-'
                		+ NEW_QUESTION.mcAnswer);
                NEW_QUESTION.questionCorrect = true;
            } else {
                NEW_QUESTION.set_correct(false, '#incorrect-text-mc-'
                		+ NEW_QUESTION.mcAnswer);
            }
        }
    },
    
    bind_events: function() {
        $('#submit-answer-button').click(function () {
        	console.log("submit-answer-button quiz:EnterKey ")
            //$(this).trigger("quiz:EnterKey");
            NEW_QUESTION.answer_submit();
        });
        
        $('[id^=Answer]').keydown(function(e) {
        	console.log("Keydown quiz:EnterKey " + e)
        	if (e.which == 13) {
        		e.preventDefault();
        		//$(this).trigger("quiz:EnterKey");
        		NEW_QUESTION.answer_submit();
        	}
        });
        
        $("#main-form input[type='radio']").click(function() {
            NEW_QUESTION.mcAnswer
            		= $("#main-form input[type='radio']:checked").val();
        	console.log("Radio clicked = " + NEW_QUESTION.mcAnswer);
        });

        $('#see-answer-button').click( function () {
            NEW_QUESTION.show_after_image();
            if (NEW_QUESTION.myQuestion.type.indexOf("SA") == 0) {
            	// short answer
                for (var i = 0; i < NEW_QUESTION.numAnswers; i++) {
                    var index = i + 1;
                    if (NEW_QUESTION.myQuestion
                    		.responses[i].correct_answer.length > 0) {
                        $('#incorrect-text-' + index).text
                            (NEW_QUESTION.myQuestion
                            		.responses[i].correct_answer);
                    }
                }
            }
        });

        $('#mark-correct-button').click(function () {
            NEW_QUESTION.set_correct(true, '#correct-text-tf');
        });

        $('#mark-incorrect-button').click(function () {
            NEW_QUESTION.set_correct(false, '#incorrect-text-tf');
        });

        $('#next-question-button').click(function () {
            window.open(context_path + "/submitanswer?select_source=" +
                    NEW_QUESTION.myQuestion.source_id + "&question_id=" + NEW_QUESTION.myQuestion.id +
                    "&answer_correct=" + NEW_QUESTION.questionCorrect, "_self");
        });
    }
}

jQuery(document).ready(function(){
    NEW_QUESTION.init();
    NEW_QUESTION.update_dom();
    NEW_QUESTION.bind_events();
})