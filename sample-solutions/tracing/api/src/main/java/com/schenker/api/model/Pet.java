package com.schenker.api.model;

import jakarta.persistence.Id;

public record Pet (@Id String id, String name, String uri, Species species){}
