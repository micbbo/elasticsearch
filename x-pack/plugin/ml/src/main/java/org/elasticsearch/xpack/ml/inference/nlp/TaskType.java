/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.ml.inference.nlp;

import org.elasticsearch.xpack.ml.inference.nlp.tokenizers.BertTokenizer;

import java.util.List;
import java.util.Locale;

public enum TaskType {

    NER {
        @Override
        public NlpTask.Processor createProcessor(BertTokenizer tokenizer, List<String> classificationLabels) {
            return new NerProcessor(tokenizer, classificationLabels);
        }
    },
    SENTIMENT_ANALYSIS {
        @Override
        public NlpTask.Processor createProcessor(BertTokenizer tokenizer, List<String> classificationLabels) {
            return new SentimentAnalysisProcessor(tokenizer, classificationLabels);
        }
    },
    FILL_MASK {
        @Override
        public NlpTask.Processor createProcessor(BertTokenizer tokenizer, List<String> classificationLabels) {
            return new FillMaskProcessor(tokenizer);
        }
    },
    BERT_PASS_THROUGH {
        @Override
        public NlpTask.Processor createProcessor(BertTokenizer tokenizer, List<String> classificationLabels) {
            return new PassThroughProcessor(tokenizer);
        }
    };

    public NlpTask.Processor createProcessor(BertTokenizer tokenizer, List<String> classificationLabels) {
        throw new UnsupportedOperationException("json request must be specialised for task type [" + this.name() + "]");
    }

    @Override
    public String toString() {
        return name().toLowerCase(Locale.ROOT);
    }

    public static TaskType fromString(String name) {
        return valueOf(name.trim().toUpperCase(Locale.ROOT));
    }
}
